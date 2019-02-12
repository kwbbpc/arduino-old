package has.arduino;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import has.communications.ISerialBaseStation;
import has.communications.ISerialPortListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author KBroadway
 * 
 *         A good chunk of this code was taken from
 *         http://arduino.cc/playground/Interfacing/Java
 * 
 *         This class implements the arduino as far as the server is concerned
 *         with it. Any commnunications the server needs to make with the
 *         Arduino are implemented here. This class does not maintain state of
 *         any kind. It is merely to represent the required communications
 *         between the arduino and the server (or whoever).
 * 
 */
public class Arduino implements ISerialBaseStation 
{

	public List<String> serialInBuffer; 
	public List<ISerialPortListener> arduinoListeners;

	SerialPort serialPort; // the port to use
	private InputStream input; // input stream from the port
	private OutputStream output; // output stream from the port
	private static final int TIME_OUT = 2000; // MS to wait for port open
	private static final int DATA_RATE = 9600; // Baud Rate

	@Override
	public void initializeDevice()
	{

		arduinoListeners = new ArrayList<ISerialPortListener>();
		serialInBuffer = new ArrayList<String>();

		CommPortIdentifier portId = null;
		Enumeration<?> portEnum = CommPortIdentifier.getPortIdentifiers();

		// iterate through, looking for the port
		while (portEnum.hasMoreElements())
		{
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum
					.nextElement();
			for (String portName : ISerialBaseStation.PORT_NAMES)
			{
				if (currPortId.getName().equals(portName))
				{
					portId = currPortId;
					break;
				}
			}
		}

		if (portId == null)
		{
			System.out.println("Could not find COM port.");
			return;
		}

		try
		{
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			// open the streams
			input = serialPort.getInputStream();
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		}
		catch (Exception e)
		{
			assert (false);
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port. This will prevent
	 * port locking on platforms like Linux.
	 */
	@Override
	public synchronized void close()
	{
		if (serialPort != null)
		{
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent)
	{

		// wait for 100 ms to get the full message
		try
		{
			Thread.sleep(50);
		}
		catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE)
		{
			try
			{
				int available = input.available();
				byte chunk[] = new byte[available];
				input.read(chunk, 0, available);
				String message = new String(chunk);

				// notify any listeners with the new message
				for (ISerialPortListener listener : arduinoListeners)
				{
					listener.serialPortReadEvent(message);
				}

			}
			catch (Exception e)
			{

				assert (false);
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other
		// ones.
	}

	@Override
	public void send(String message)
	{

		try
		{
			byte[] byteMessage = message.getBytes();

			output.write(byteMessage);

		}
		catch (IOException e)
		{
			System.err
					.println(e.toString() + ": error while trying to write message [" + message + "]");
		}

	}

	@Override
	public void connectSerialPortListener(ISerialPortListener listener)
	{

		arduinoListeners.add(listener);

	}
}

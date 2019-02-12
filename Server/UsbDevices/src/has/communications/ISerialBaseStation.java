package has.communications;

import gnu.io.SerialPortEventListener;

/**
 * @author KBroadway
 * 
 *         This interface is meant to deal specifically with any device that
 *         uses the RxTxComm libraries.
 * 
 */
public interface ISerialBaseStation extends SerialPortEventListener
{

	/**
	 * 
	 * Commonly used serial ports
	 *  
	 */
	public static final String PORT_NAMES[] = { "/dev/tty.usbserial-A9007UX1", // Mac
																				// OS
																				// X
	"/dev/ttyUSB0", // Linux 
	"COM3", // Windows
	"COM4", // Windows alternate port
	"COM6" };

	/**
	 * Perform device initialization, such as opening a com port for
	 * communications.
	 * 
	 */
	public void initializeDevice();

	public void connectSerialPortListener(ISerialPortListener listener);

	/**
	 * Sends data to the base station
	 * 
	 * @param parameter
	 *            Any parameter that implements the ISendParameter to be sent to
	 *            the device.
	 * 
	 */
	public void send(String message);

	/**
	 * 
	 * Closes the usb port
	 * 
	 */
	public void close();

}

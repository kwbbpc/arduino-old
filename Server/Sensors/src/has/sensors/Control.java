package has.sensors;

import has.sensors.core.ISensorParameters;

/**
 * @author KBroadway
 * 
 *         Allows for actions to be sent to a sensor.
 */
public interface Control
{

	/**
	 * Sends the data stored in this control
	 * 
	 * @return
	 */
	public void sendData(ISensorParameters parameters);

}

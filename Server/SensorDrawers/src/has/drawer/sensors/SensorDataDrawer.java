package has.drawer.sensors;

import has.sensors.HasUnit;

public interface SensorDataDrawer
{

	/**
	 * Draws abbreviated data about the unit, commonly the current state.
	 * 
	 * @param unit
	 *            the unit to represent
	 * @return html string
	 */
	public String drawShortDetails(HasUnit unit);

	/**
	 * Draws expanded data about the unit, commonly current state and other
	 * important details
	 * 
	 * @param unit
	 *            the unit to represent
	 * @return html string
	 */
	public String drawExpandedDetails(HasUnit unit);

	public String drawIcon(HasUnit unit, String encodedUrl);

	public String drawControlForm(HasUnit unit, String encodedUrl);

}

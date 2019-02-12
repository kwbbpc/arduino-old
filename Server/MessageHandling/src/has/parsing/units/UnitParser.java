package has.parsing.units;

import has.sensors.core.ISensorParameters;

import java.util.List;

public interface UnitParser
{

	/**
	 * Unit specific parsing function to pull out all the fields of data from
	 * the Has Message.
	 * 
	 * @param data
	 *            a list of comma delineated data members
	 * @return The senor's parameters
	 */
	public ISensorParameters parse(List<String> data);

	/**
	 * Formats ISensorParameters data into a comma delineated String that
	 * matches ICD parameter order.
	 * 
	 * @param parameters
	 * @return Parameter string
	 */
	public String formatData(ISensorParameters parameters);

}

package has.drawer.sensors;

import has.sensors.HasUnit;
import has.sensors.Sensor;
import has.sensors.parameters.HumidityParameters;
import has.sensors.state.SensorState;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class HumidityDataDrawer implements SensorDataDrawer
{

	private static final Map<SensorState, String> imgMap;

	static
	{
		imgMap = new HashMap<SensorState, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(null, "img/humidity.png");
			}
		};
	}

	@Override
	public String drawShortDetails(HasUnit unit)
	{
		try
		{
			Sensor sensor = (Sensor) unit;
			HumidityParameters params = (HumidityParameters) sensor
					.getParameters();

			String details = "";

			// format as a single decimal
			double humidity = params.getHumidity();
			DecimalFormat twoDecimal = new DecimalFormat("#,##0.00");
			details = twoDecimal.format(humidity);

			return details;

		} catch (Exception e)
		{
			return "";
		}
	}

	@Override
	public String drawExpandedDetails(HasUnit unit)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String drawIcon(HasUnit unit, String encodedUrl)
	{

		return "<img src='" + imgMap.get(null) + "' />";

	}

	@Override
	public String drawControlForm(HasUnit unit, String encodedUrl)
	{
		// no controls to draw
		return null;
	}

}

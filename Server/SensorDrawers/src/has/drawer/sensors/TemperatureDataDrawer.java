package has.drawer.sensors;

import has.sensors.HasUnit;
import has.sensors.Sensor;
import has.sensors.parameters.TemperatureParameters;
import has.sensors.state.SensorState;

import java.util.HashMap;
import java.util.Map;

public class TemperatureDataDrawer implements SensorDataDrawer
{

	private static final Map<SensorState, String> imgMap;

	static
	{
		imgMap = new HashMap<SensorState, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(null, "img/thermometer.png");
			}
		};
	}

	@Override
	public String drawShortDetails(HasUnit unit)
	{
		try
		{
			Sensor sensor = (Sensor) unit;
			TemperatureParameters params = (TemperatureParameters) sensor
					.getParameters();

			String details = "";

			int temp = params.getTemperature();
			details = Integer.toString(temp) + "&deg; F";

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
		// nothing special to draw here.
		return "<img src='" + imgMap.get(null) + "' />";

	}

	@Override
	public String drawControlForm(HasUnit unit, String encodedUrl)
	{
		//
		return null;
	}
}

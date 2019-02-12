package has.drawer.sensors;

import has.sensors.HasUnit;
import has.sensors.Sensor;
import has.sensors.parameters.LightSensorParameters;
import has.sensors.state.BrightnessState;
import has.sensors.state.SensorState;

import java.util.HashMap;
import java.util.Map;

public class BrightnessDataDrawer implements SensorDataDrawer
{

	private static final Map<SensorState, String> imgMap;

	static
	{
		imgMap = new HashMap<SensorState, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(null, "img/brightness.png");
			}
		};
	}

	@Override
	public String drawShortDetails(HasUnit unit)
	{
		try
		{
			Sensor sensor = (Sensor) unit;
			LightSensorParameters params = (LightSensorParameters) sensor
					.getParameters();

			String details = "";

			// format as a single decimal
			int lightLevel = params.getLightLevel();

			BrightnessState state = BrightnessState
					.convertToBrightness(lightLevel);

			return state.toString();

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

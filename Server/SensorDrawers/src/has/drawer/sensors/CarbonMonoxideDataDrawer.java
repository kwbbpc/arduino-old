package has.drawer.sensors;

import has.sensors.HasUnit;
import has.sensors.Sensor;
import has.sensors.parameters.CarbonMonoxideParameters;
import has.sensors.state.SensorState;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CarbonMonoxideDataDrawer implements SensorDataDrawer
{

	private static final Map<SensorState, String> imgMap;

	static
	{
		imgMap = new HashMap<SensorState, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(null, "img/co.png");
			}
		};
	}

	@Override
	public String drawShortDetails(HasUnit unit)
	{
		try
		{
			Sensor sensor = (Sensor) unit;

			CarbonMonoxideParameters params = (CarbonMonoxideParameters) sensor
					.getParameters();

			String details = "";

			// format as a two decimal
			double ppm = params.getCoPpm();
			DecimalFormat twoDecimal = new DecimalFormat("#,##0.00");
			details = twoDecimal.format(ppm) + " ppm";

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

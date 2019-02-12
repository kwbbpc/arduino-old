package has.drawer.sensors;

import has.drawer.page.BasicElementDrawer;
import has.sensors.HasUnit;
import has.sensors.Sensor;
import has.sensors.parameters.BlindsParameters;
import has.sensors.state.BlindState;
import has.sensors.state.SensorState;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class BlindsDataDrawer implements SensorDataDrawer
{

	private static final Map<SensorState, String> imgMap;

	static
	{
		imgMap = new HashMap<SensorState, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(null, "img/blinds_close.png");
				put(BlindState.CLOSED_DOWN, "img/blinds_close.png");
				put(BlindState.OPEN_90, "img/blinds_open.png");
				put(BlindState.CLOSED_UP, "img/blinds_close.png");
			}
		};
	}

	@Override
	public String drawShortDetails(HasUnit unit)
	{
		try
		{
			Sensor sensor = (Sensor) unit;

			String details = "";

			BlindsParameters parameters = (BlindsParameters) sensor
					.getParameters();

			// format as a single decimal
			int angle = parameters.getDegreesRotation();

			BlindState state = BlindState.getState(angle);

			details = state.toString();

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

		Sensor sensor = (Sensor) unit;
		BlindsParameters params = (BlindsParameters) sensor.getParameters();

		JSONObject obj = new JSONObject();

		obj.put("up", imgMap.get(BlindState.CLOSED_UP));
		obj.put("open", imgMap.get(BlindState.OPEN_90));
		obj.put("down", imgMap.get(BlindState.CLOSED_DOWN));

		return "<img class='clickableImg' src='"
				+ imgMap.get(BlindState.getState(params.getDegreesRotation()))
				+ "' onclick='onBlindsClick(this," + obj.toJSONString() + ",\""
				+ unit.getUniqueId().toString() + "\");'/>";

	}

	@Override
	public String drawControlForm(HasUnit unit, String encodedUrl)
	{

		StringBuilder ctrl = new StringBuilder();

		// Add a checkbox for the sensor
		ctrl.append(BasicElementDrawer.drawHasCheckbox(unit.getUniqueId()));

		Sensor blinds = (Sensor) unit;

		BlindsParameters params = (BlindsParameters) blinds.getParameters();

		ctrl.append("<input type='hidden' id='" + unit.getUniqueId()
				+ "_value' name='" + unit.getUniqueId() + "' value='"
				+ BlindState.getState(params.getDegreesRotation()).toString()
				+ "'>");

		ctrl.append(UnitDrawer.drawIcon(unit, encodedUrl));

		// print this sensor name
		boolean isIncludeData = false;
		ctrl.append(UnitDrawer.drawClickableLabel(unit, encodedUrl,
				isIncludeData));

		return ctrl.toString();
	}
}

package has.drawer.sensors;

import has.drawer.page.BasicElementDrawer;
import has.sensors.HasUnit;
import has.sensors.Sensor;
import has.sensors.parameters.LightSwitchParameters;
import has.sensors.state.LightSwitchState;
import has.sensors.state.SensorState;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class LightSwitchDataDrawer implements SensorDataDrawer
{

	private static final Map<SensorState, String> imgMap;

	static
	{
		imgMap = new HashMap<SensorState, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(LightSwitchState.ON, "img/lightbulb_on.png");
				put(LightSwitchState.OFF, "img/lightbulb_off.png");
			}
		};
	}

	@Override
	public String drawShortDetails(HasUnit unit)
	{
		// no additional details to draw.
		return "";
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
		LightSwitchParameters params = (LightSwitchParameters) sensor
				.getParameters();

		JSONObject obj = new JSONObject();

		obj.put("on", imgMap.get(LightSwitchState.ON));
		obj.put("off", imgMap.get(LightSwitchState.OFF));

		return "<img class='clickableImg' src='"
				+ imgMap.get(params.getState())
				+ "' onclick='onLightSwitchClick(this," + obj.toJSONString()
				+ ",\"" + unit.getUniqueId().toString() + "\");'/>";

	}

	@Override
	public String drawControlForm(HasUnit unit, String encodedUrl)
	{
		StringBuilder ctrl = new StringBuilder();

		// Add a checkbox for the sensor
		ctrl.append(BasicElementDrawer.drawHasCheckbox(unit.getUniqueId()));

		// get the current setting of the unit
		Sensor lightSwitch = (Sensor) unit;

		LightSwitchParameters params = (LightSwitchParameters) lightSwitch
				.getParameters();

		ctrl.append("<input type='hidden' id='" + unit.getUniqueId()
				+ "_value' name='" + unit.getUniqueId() + "' value='"
				+ params.getState().toString() + "'>");

		ctrl.append(drawIcon(unit, encodedUrl));

		// print this sensor name
		boolean isIncludeData = false;
		ctrl.append(UnitDrawer.drawClickableLabel(unit, encodedUrl,
				isIncludeData));

		return ctrl.toString();
	}
}

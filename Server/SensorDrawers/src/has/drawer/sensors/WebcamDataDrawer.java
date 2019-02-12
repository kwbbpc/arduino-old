package has.drawer.sensors;

import has.definitions.CommonParams;
import has.definitions.FormValue;
import has.definitions.PageType;
import has.sensors.HasUnit;
import has.sensors.impl.SensorWebcamServo;
import has.sensors.state.SensorState;

import java.util.HashMap;
import java.util.Map;

public class WebcamDataDrawer implements SensorDataDrawer
{

	private static final Map<SensorState, String> imgMap;

	static
	{
		imgMap = new HashMap<SensorState, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(null, "img/webcam.png");
			}
		};
	}

	@Override
	public String drawShortDetails(HasUnit unit)
	{

		return "";

	}

	@Override
	public String drawExpandedDetails(HasUnit unit)
	{

		if (unit instanceof SensorWebcamServo)
		{
		}
		// start the stream

		return "";
	}

	@Override
	public String drawIcon(HasUnit unit, String encodedUrl)
	{
		String htmlString = "<img class='clickableImg' src='"
				+ imgMap.get(null) + "' onclick=\"location.href='" + encodedUrl
				+ "?";
		htmlString += FormValue.unitEditId + "=" + unit.getUniqueId() + "&";
		htmlString += CommonParams.page + "=" + PageType.UnitEditValidate;
		htmlString += "'\"/>";

		return htmlString;

	}

	@Override
	public String drawControlForm(HasUnit unit, String encodedUrl)
	{
		// no additional controls to draw
		return null;
	}
}

package has.parsing;

import has.parsing.impl.FormatStrategyAction;
import has.parsing.impl.ParseStrategyStatus;
import has.sensors.core.ISensorParameters;
import has.sensors.core.ISensorUpdaterCallback;
import has.sensors.core.TypeSensor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class HasMessenger
{

	public static final String signature = "HASMESSAGE";

	private Map<Integer, IParseStrategy> parsingStrategyMap;
	private Map<Integer, IFormatStrategy> formatStrategyMap;

	public HasMessenger()
	{

		parsingStrategyMap = new HashMap<Integer, IParseStrategy>();
		formatStrategyMap = new HashMap<Integer, IFormatStrategy>();

		// @formatter:off
			parsingStrategyMap.put(		MessageId.Type.STATUS.toId(),			new ParseStrategyStatus());
			
			
			formatStrategyMap.put(		MessageId.Type.ACTION.toId(), 			new FormatStrategyAction());
			
			//nessecary?
			//formatStrategyMap.put(		MessageId.Type.STATUS_QUERY.toId(), 	new FormatStrategyStatusQuery());
			
			
		// @formatter:on

	}

	public void ParseMessage(String message, ISensorUpdaterCallback updater)
	{

		// wipe out any whitespace, returns, newlines, etc.
		message = message.replaceAll("\\s", "");
		message = message.replaceAll("\r", "");
		message = message.replaceAll("\n", "");

		List<String> params = new ArrayList<String>();

		// extract the comma delineated data
		StringTokenizer tokens = new StringTokenizer(message, ",");

		// check for validity
		if (!tokens.nextToken().equals(signature))
		{
			System.err.println("Invalid message received.");
			System.err.println("[" + message + "]");
			return; // discard and return.
		}

		String next = tokens.nextToken();

		// save the message id
		MessageId.Type messageId = MessageId.find(next);

		// if the message Id isn't valid
		if (messageId == null || messageId == MessageId.Type.INVALID)
			return; // discard and return

		// save the reset of the message for the strategy parsing
		while (tokens.hasMoreTokens())
			params.add(tokens.nextToken());

		// look up the proper way to interpret the message and execute.
		IParseStrategy strategy = parsingStrategyMap.get(messageId.toId());
		strategy.process(params, updater);

	}

	public String FormatAndSendMessage(MessageId.Type messageType,
			String location, TypeSensor type, ISensorParameters parameters)
	{

		// look up the proper way to interpret the message and execute.
		IFormatStrategy strategy = formatStrategyMap.get(messageType.toId());
		return strategy.Format(location, type, parameters);
	}
}

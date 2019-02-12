package has.parsing;

public class MessageId
{

	public enum Type
	{

		//@formatter:off
		DISCOVER(			0x01),
		STATUS_QUERY(		0x02),
		STATUS(				0x03),
		ACTION(				0x04),
		
		INVALID(			0xFF);
			
		//@formatter:on

		int id_;

		Type(Integer id)
		{
			this.id_ = id;
		}

		public Integer toId()
		{
			return id_;
		}

	}

	public static Type find(String id)
	{
		for (Type t : Type.values())
		{
			if (id.equals(t.toId().toString()))
				return t;
		}

		return Type.INVALID;
	}

}

package common.report;

public class Sum implements IStatis {
	private Column c;

	@Override
	public void setColumn(Column c) {
		this.c = c;
	}

	@Override
	public String statis() {
		// TODO Auto-generated method stub
		return " sum(" + c.toString() + ") ";
	}

}

package common.report;

public class ReportModels {
	public static ReportSetGenerate CountReportSetModel = new ReportSetGenerate() {
		@Override
		public ReportSet change(Object[] objs) {
			ReportSet set = new ReportSet();
			set.setCount(objs[0].toString());
			set.setName((String) objs[1]);
			return set;
		}

	};
	
	public static ReportSetStrGenerate CountReportRStrModel = new ReportSetStrGenerate() { 
		@Override
		public String change(ReportSet s) {
			return "['" + s.getName() + "'," + s.getCount() + "]";
		}
	};
}

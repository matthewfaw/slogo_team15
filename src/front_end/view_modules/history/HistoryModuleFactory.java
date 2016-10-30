package front_end.view_modules.history;

public class HistoryModuleFactory {

	private HistoryModuleFactory(){
		// Does Nothing
	}
	
	public static IHistoryModule build(){
		return new ConcreteHistoryModule();
	}
	
}

package pl.mkorwel.app.manager.base.usecase;

public interface BaseUserCase<R, T> {

	R execute(T param);
	
}

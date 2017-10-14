package componente;

import javafx.util.StringConverter;

public class StringConverterBean<T extends RenderizaCombo> extends StringConverter<T> {

	@Override
	public String toString(T object) {
		if (object == null) {
			return null;
		} else {
			return object.getText();
		}
	}

	@Override
	public T fromString(String string) {
		// se for necessario converter de uma string para um objeto.
		// N�o vai ser preciso no nosso caso
		return null;
	}

}

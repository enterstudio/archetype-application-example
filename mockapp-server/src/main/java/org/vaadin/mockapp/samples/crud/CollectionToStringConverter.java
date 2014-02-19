package org.vaadin.mockapp.samples.crud;

import java.util.Collection;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public class CollectionToStringConverter implements
		Converter<String, Collection> {

	@Override
	public Collection convertToModel(String value,
			Class<? extends Collection> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		throw new UnsupportedOperationException(
				"Can only convert from collection to string");
	}

	@Override
	public String convertToPresentation(Collection value,
			Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		StringBuilder b = new StringBuilder();
		for (Object o : value) {
			b.append(o.toString());
			b.append(", ");
		}
		return b.substring(0, b.length() - 2);

	}

	@Override
	public Class<Collection> getModelType() {
		return Collection.class;
	}

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}

}

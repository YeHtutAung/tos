package com.rex.pos.common;

import java.util.Base64;

import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CryptoConverter implements AttributeConverter<String, String> {

	private static final String SECRET_KEY = "Rex!@#rexiee.com"; // Store in env vars
	private static final String SALT = KeyGenerators.string().generateKey();

	private final AesBytesEncryptor encryptor = new AesBytesEncryptor(SECRET_KEY, SALT);

	@Override
	public String convertToDatabaseColumn(String attribute) {
		if (attribute == null)
			return null;
		byte[] encrypted = encryptor.encrypt(attribute.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		if (dbData == null)
			return null;
		byte[] decrypted = encryptor.decrypt(Base64.getDecoder().decode(dbData));
		return new String(decrypted);
	}

}

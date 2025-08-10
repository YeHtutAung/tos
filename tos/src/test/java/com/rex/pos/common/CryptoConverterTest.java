package com.rex.pos.common;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

class CryptoConverterTest {

	private CryptoConverter cryptoConverter;
	private static final String SECRET_KEY = "Rex!@#rexiee.com"; // 32 chars
	private static final String SALT = KeyGenerators.string().generateKey();

	@BeforeEach
	void setUp() throws Exception {
		AesBytesEncryptor encryptor = new AesBytesEncryptor(SECRET_KEY, SALT);
		cryptoConverter = new CryptoConverter();
		Field encryptorField = CryptoConverter.class.getDeclaredField("encryptor");
        encryptorField.setAccessible(true);
        encryptorField.set(cryptoConverter, encryptor);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		String originalText = "sensitive-data@example.com";

		// Encrypt
		String encrypted = cryptoConverter.convertToDatabaseColumn(originalText);
		assertNotNull(encrypted);
		assertNotEquals(originalText, encrypted); // Ensure data is encrypted

		// Decrypt
		String decrypted = cryptoConverter.convertToEntityAttribute(encrypted);
		assertEquals(originalText, decrypted); // Ensure round-trip works
	}

	@Test
	void testEncrypt_NullInput() {
		assertNull(cryptoConverter.convertToDatabaseColumn(null));
	}

	@Test
	void testDecrypt_NullInput() {
		assertNull(cryptoConverter.convertToEntityAttribute(null));
	}

}

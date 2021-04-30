package com.swapi.http;

import java.io.IOException;

public interface HttpCall {
	public CallResponse get() throws IOException;
}

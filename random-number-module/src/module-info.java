import com.example.random.service.business.FastRandomNumberService;
import com.example.random.service.business.SecureRandomNumberService;
import com.example.random.service.RandomNumberService;

module com.example.random {
	exports com.example.random.service;

	provides RandomNumberService with FastRandomNumberService, SecureRandomNumberService;
}
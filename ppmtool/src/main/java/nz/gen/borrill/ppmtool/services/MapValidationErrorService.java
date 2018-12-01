package nz.gen.borrill.ppmtool.services;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import static java.util.stream.Collectors.toMap;

@Service
public class MapValidationErrorService {
	
	public ResponseEntity<?> getErrorResponse(BindingResult result) {
		if (result.hasErrors()) {
			// TODO - conversion to map throws away errors if there is more one for a field
			Map<String, String> errors = 
				result.getFieldErrors().stream().collect(toMap(FieldError::getField, FieldError::getDefaultMessage, (e1, e2) -> e1));			
			return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
		}
		return null;
	}

}

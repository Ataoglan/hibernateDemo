package exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) /*@ResponseStatus(value = HttpStatus.NOT_FOUND) */

public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue){

        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.resourceName = resourceName;

    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

}
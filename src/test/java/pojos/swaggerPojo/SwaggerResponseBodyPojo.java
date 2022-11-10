package pojos.swaggerPojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwaggerResponseBodyPojo {

    private Integer code;
    private String type;
    private String message;

    public SwaggerResponseBodyPojo(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public SwaggerResponseBodyPojo() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SwaggerResponsePojo{" +
                "code=" + code +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

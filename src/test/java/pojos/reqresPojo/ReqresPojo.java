package pojos.reqresPojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqresPojo {


    private String name;
    private String job;

    public ReqresPojo(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public ReqresPojo() {
    }

    public ReqresPojo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "ReqresPojo{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }

}

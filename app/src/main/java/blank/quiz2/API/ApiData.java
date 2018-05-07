package blank.quiz2.API;

public class ApiData<T> {
    private String respon;
    private T data;

    public ApiData(){}

    public ApiData(String status, T data) {
        this.respon = status;
        this.data = data;
    }

    public String getRespon() {
        return respon;
    }

    public void setRespon(String status) {
        this.respon = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

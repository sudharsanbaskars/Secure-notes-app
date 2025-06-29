package com.project.secure.notes.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class APIResponse {
    private String path;
    private boolean success;
    private int statusCode;
    private Object details;

    public static class APIResponseBuilder{
        private final APIResponse appResponse;
        public APIResponseBuilder(){
            appResponse = new APIResponse();
        }

        public APIResponseBuilder setPath(String path){
            appResponse.setPath(path);
            return this;
        }

        public APIResponseBuilder setDetails(Object details){
            appResponse.setDetails(details);
            return this;
        }

        public APIResponseBuilder setSuccess(boolean success){
            appResponse.setSuccess(success);
            return this;
        }

        public APIResponseBuilder setStatusCode(int statusCode){
            appResponse.setStatusCode(statusCode);
            return this;
        }

        public APIResponse build(){
            return appResponse;
        }
    }
}

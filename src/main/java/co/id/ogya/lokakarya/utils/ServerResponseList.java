package co.id.ogya.lokakarya.utils;

import org.springframework.http.HttpStatus;

public class ServerResponseList {
    public ResponseDetail getInfoOk(String msg, long execTime) {
        ResponseDetail info = new ResponseDetail();
        info.setStatus(HttpStatus.OK.value());
        info.setMessage(HttpStatus.OK.name());
        info.setDetailMessage(msg);
        info.setDetailInfo(HttpStatus.OK);
        info.setExecutionTime(String.valueOf(execTime));

        return info;
    }

    public ResponseDetail getInfoUnauthorized(String msg) {
        ResponseDetail info = new ResponseDetail();
        info.setStatus(HttpStatus.UNAUTHORIZED.value());
        info.setMessage(HttpStatus.UNAUTHORIZED.name());
        info.setDetailMessage(msg);
        info.setDetailInfo(HttpStatus.UNAUTHORIZED);

        return info;
    }

    public ResponseDetail getInfoBadRequest(String msg) {
        ResponseDetail info = new ResponseDetail();
        info.setStatus(HttpStatus.BAD_REQUEST.value());
        info.setMessage(HttpStatus.BAD_REQUEST.name());
        info.setDetailMessage(msg);
        info.setDetailInfo(HttpStatus.BAD_REQUEST);

        return info;
    }

    public ResponseDetail getInfoInternalServerError(String msg) {
        ResponseDetail info = new ResponseDetail();
        info.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        info.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
        info.setDetailMessage(msg);
        info.setDetailInfo(HttpStatus.INTERNAL_SERVER_ERROR);

        return info;
    }
}

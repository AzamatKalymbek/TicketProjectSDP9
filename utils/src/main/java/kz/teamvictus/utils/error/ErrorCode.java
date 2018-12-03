package kz.teamvictus.utils.error;

public class ErrorCode {

    // HashMap params;
    public enum ErrorCodes {
        SYSTEM_ERROR,
        CONNECTION_ERROR,
        AUTH_ERROR,
        PASSWORDS_NOT_MATCH,
        EMPTY_SPRAVKA_CODE,
        EMPTY_SPRAVKA,
        SPRAVKA_NOT_FOUND,
        USER_NOT_FOUND,
        EMPTY_CREDENTIALS,
        NOT_AUTHENTICATED,
        EMPTY_DOCUMENTTYPE_CODE,
        EMPTY_EDUCATIONFORM_CODE,
        EMPTY_SPECIALITY_CODE,
        EMPTY_PAYMENTSOURCE_CODE,
        EMPTY_STAFFACADEMICDEGREE_EMPTY,
        EMPTY_DEPARTMENT_CODE,
        EMPTY_INFORMATIONTABLE_CODE,
        USER_EXISTS,
        INVALID_IIN_FORMAT,
        INVALID_EMAIL_FORMAT,
        TOO_LARGE_FILE_SIZE,
        ESUVO_REPORT_SYNC_DISABLED,
        INVALID_DATE_FORMAT,
        ORDER_CONFIRMED
    }

    public static String toString(ErrorCodes errorRef) {
        return errorRef.toString();
    }
}

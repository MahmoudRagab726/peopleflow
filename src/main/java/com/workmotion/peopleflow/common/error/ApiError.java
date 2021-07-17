package com.workmotion.peopleflow.common.error;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/16/2021 by OLE
 */
@Data
@AllArgsConstructor
public class ApiError {
    private int code;
    private String message;
    private String description;
}

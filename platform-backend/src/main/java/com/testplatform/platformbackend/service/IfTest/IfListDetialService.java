package com.testplatform.platformbackend.service.IfTest;

import com.testplatform.platformbackend.entity.IfListDetial;

public interface IfListDetialService {
    IfListDetial getDetial(int id);
    int save(IfListDetial detial);
}

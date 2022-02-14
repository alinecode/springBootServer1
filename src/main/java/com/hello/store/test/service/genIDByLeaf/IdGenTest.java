package com.hello.store.test.service.genIDByLeaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencent.devops.leaf.common.Result;
import com.tencent.devops.leaf.service.SegmentService;

/**
 * 使用美团开源的leaf生成id
 * @author AL
 *
 */
@RestController
@RequestMapping("/genidTest")
public class IdGenTest {

	@Autowired
    private SegmentService segmentService;
	
	@RequestMapping("/1")
	public long test() {
		
		Result result = segmentService.getId("leaf-segment-test");
		long id2 = result.getId();
		
		return id2;
		
	}
}

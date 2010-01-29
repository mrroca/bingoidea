package cn.com.bsoft.contrl;

import java.util.HashMap;

public class ConditionEntity {

	private static ConditionEntity mCe = null;

	private static HashMap<String, ConditionEntity> mCEHashMap = null;

	static {
		if (mCEHashMap == null) {
			mCEHashMap = new HashMap<String, ConditionEntity>();
		}
	}

	private ConditionEntity() {
	}

	public static ConditionEntity getInstance() {
		if (mCe == null) {
			mCe = new ConditionEntity();
		}
		return mCe;
	}

	public static ConditionEntity getInstance(String objectName) {
		ConditionEntity ce = getConditionEntityByKey(objectName);
		if (ce == null) {
			ce = new ConditionEntity();
			mCEHashMap.put(objectName.toUpperCase(), ce);
		}
		return ce;
	}

	private static ConditionEntity getConditionEntityByKey(String key) {
		return (ConditionEntity) mCEHashMap.get(key.toUpperCase());
	}
}

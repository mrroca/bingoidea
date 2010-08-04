package cn.com.bsoft.proxy.xmlbuild.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.com.bsoft.proxy.entity.TableEntity;
import cn.com.bsoft.proxy.xmlbuild.XMLEltBuilder;

public class XMLEltBuilderImpl implements XMLEltBuilder {

	private String mKey = "";

	private String mParentTabName = "";

	private List<Element> mTableEles = null;

	private HashMap<?, ?> mTableHM = null;

	public XMLEltBuilderImpl(HashMap<?, ?> hm) {
		mTableEles = new ArrayList<Element>();
		mTableHM = (HashMap<?, ?>) hm.clone();
	}

	public Element toXMLEltBuild() {
		String pEleName = "";
		HashMap<String, Element> eleHM = new HashMap<String, Element>();
		HashMap<String, String> relatedHM = new HashMap<String, String>();

		disintegeate(eleHM, relatedHM);

		Iterator<String> iter = relatedHM.keySet().iterator();
		while (iter.hasNext()) {
			String tabname = (String) iter.next();
			String related = (String) relatedHM.get(tabname);
			Element parent = (Element) eleHM.get(related);
			Element child = (Element) eleHM.get(tabname);
			if (parent == null) {
				pEleName = tabname;
				continue;
			}
			parent.add(child);
			eleHM.put(related, parent);
		}
		mParentTabName = pEleName;
		mKey = ((TableEntity) mTableHM.get(pEleName)).getPK();

		destroy();

		return (Element) eleHM.get(pEleName);
	}

	private void disintegeate(HashMap<String, Element> eleHM, HashMap<String, String> related) {
		if (mTableHM != null) {
			List<Element> eles = new ArrayList<Element>();
			Iterator<?> iter = mTableHM.keySet().iterator();
			while (iter.hasNext()) {
				String tabName = (String) iter.next();
				TableEntity te = (TableEntity) mTableHM.get(tabName);
				Element ele = DocumentHelper.createElement(te.getTableName());
				List<?> collist = te.getColName();
				List<?> filters = te.getFilterList();
				Iterator<?> colIter = collist.iterator();
				while (colIter.hasNext()) {
					String colname = (String) colIter.next();
					if (!filters.contains(colname)) {
						ele.addAttribute(colname, "");
					}
				}
				eles.add(ele);
				eleHM.put(tabName, ele);
				related.put(tabName, te.getRelated());
			}
			mTableEles = eles;
		}
	}

	private void destroy() {
		mTableHM.clear();
		mTableHM = null;
	}

	public String getKeyName() {
		return mKey;
	}

	public String getParentTableName() {
		return mParentTabName;
	}

	public List<Element> getElts() {
		return mTableEles;
	}
}

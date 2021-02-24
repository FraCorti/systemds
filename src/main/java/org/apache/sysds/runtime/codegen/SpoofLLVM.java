package org.apache.sysds.runtime.codegen;

import org.apache.sysds.hops.codegen.cplan.*;
import org.apache.sysds.runtime.instructions.cp.ScalarObject;
import org.apache.sysds.runtime.matrix.data.MatrixBlock;

import java.util.ArrayList;

public class SpoofLLVM extends SpoofOperator{

	private final CNodeTpl cnt;
	public final String name;

	public SpoofLLVM(CNodeTpl cnode) {
		name = "codegen." + cnode.getVarname();
		cnt = cnode;
	}

	public String getName() {
		return name;
	}

	public CNodeTpl getCNodeTemplate() {
		return cnt;
	}

	public String getSpoofTemplateType() {
		if (cnt instanceof CNodeCell)
			return "CW";
		else if(cnt instanceof CNodeRow)
			return "RA";
		else if(cnt instanceof CNodeMultiAgg)
			return "MA";
		else if(cnt instanceof CNodeOuterProduct)
			return "OP";
		else
			throw new RuntimeException("unknown spoof operator type");
	}

	@Override public MatrixBlock execute(ArrayList<MatrixBlock> inputs, ArrayList<ScalarObject> scalars,
		MatrixBlock out) {
		return null;
	}

	@Override public String getSpoofType() {
		String[] tmp = getClass().getName().split("\\.");
		return  tmp[tmp.length-1] + "_" + getSpoofTemplateType() + "_" + name.split("\\.")[1];
	}
}

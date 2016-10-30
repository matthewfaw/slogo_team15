package back_end.model.node.inner_nodes;

import java.util.List;

import back_end.model.node.INode;

public interface IInnerNode extends INode {

	public List<INode> getChildren();
	public void addChildren(List<INode> aChildren);

}

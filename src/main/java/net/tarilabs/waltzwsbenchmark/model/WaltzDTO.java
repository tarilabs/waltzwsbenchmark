package net.tarilabs.waltzwsbenchmark.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.drools.benchmark.waltz.Line;
import org.drools.benchmark.waltz.Stage;

/**
 * A simple dto to hold reference to {@link org.drools.benchmark.waltz.Line}s and {@link org.drools.benchmark.waltz.Stage} on the webservice call
 * 
 * @author tari
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="WaltzDTO")
public class WaltzDTO {
	
	@XmlElement(name="Line", required=true)
	protected List<Line> line;
	
	@XmlElement(name="Stage", required=true)
	protected Stage stage;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public List<Line> getLine() {
		if (line==null) {
			line = new ArrayList<Line>();
		}
		return this.line;
	}
	
	
}

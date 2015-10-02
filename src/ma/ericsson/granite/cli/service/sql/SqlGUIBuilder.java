package ma.ericsson.granite.cli.service.sql;

import static ma.ericsson.granite.cli.util.ParserConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.ericsson.granite.cli.model.GUI;
import ma.ericsson.granite.cli.service.GUIBuilder;
import ma.ericsson.granite.cli.service.sql.impl.BaseSqlBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class SqlGUIBuilder implements GUIBuilder<List<GUI>, Map<String, List<String>>> {

	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	private ApplicationContext applicationContext;

	private List<BaseSqlBuilder> builders;

	public void setBuilders(List<BaseSqlBuilder> builders) {
		this.builders = builders;
	}

	public Map<String, List<String>> build(List<GUI> guis) {

		Map<String, List<String>> queriesMap = new HashMap<String, List<String>>();

		for (GUI gui : guis) {
			
			List<String> queries = new ArrayList<String>();
			
			for (BaseSqlBuilder builder : builders) {
				queries.addAll(builder.build(gui));
			}

			queries.add(SQL_COMMIT);
			queries.add(SQL_BLANK_LINE);
			queries.add(SQL_QUIT);
			
			queriesMap.put(gui.getName(), queries);
		}

		return queriesMap;
	}
}

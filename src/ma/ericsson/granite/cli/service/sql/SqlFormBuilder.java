package ma.ericsson.granite.cli.service.sql;

import ma.ericsson.granite.cli.model.Form;
import ma.ericsson.granite.cli.service.GUIBuilder;
import ma.ericsson.granite.cli.service.sql.impl.BaseSqlFormBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static ma.ericsson.granite.cli.util.ParserConstants.*;

public class SqlFormBuilder implements GUIBuilder<Form, List<String>> {

	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	private ApplicationContext applicationContext;

	private List<BaseSqlFormBuilder> builders;

	public void setBuilders(List<BaseSqlFormBuilder> builders) {
		this.builders = builders;
	}

	public List<String> build(Form form) {

		List<String> queries = new ArrayList<String>();
		for (BaseSqlFormBuilder builder : builders) {
			queries.addAll(builder.build(form));
		}

		queries.add(SQL_COMMIT);
		queries.add(SQL_BLANK_LINE);
		queries.add(SQL_QUIT);

		return queries;
	}
}

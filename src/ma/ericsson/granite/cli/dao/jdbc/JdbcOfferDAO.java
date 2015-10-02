package ma.ericsson.granite.cli.dao.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import ma.ericsson.granite.cli.dao.OfferDAO;

public class JdbcOfferDAO implements OfferDAO {

	protected final Log log = LogFactory.getLog(getClass());
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Required
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getOfferCode(int idOffer) {
		return jdbcTemplate.queryForObject("SELECT CODE_OFFRE FROM OFFRE WHERE ID_OFFRE = ?", new Object[] { idOffer }, String.class);
	}

	public int getProductId(int idOffer, String productCode) {
		Integer productId;
		try {
			productId = jdbcTemplate.queryForObject(
					"SELECT ID_PRODUIT FROM PROD_OFFRE PO JOIN PRODUIT P USING(ID_PRODUIT) WHERE PO.ID_OFFRE = ? AND P.CODE_PRODUIT = ?",
					new Object[] { idOffer, productCode }, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
		return productId;
	}

	public String getProductDescription(int productId) {
		return jdbcTemplate.queryForObject("SELECT LIBELLE_PRODUIT FROM PRODUIT WHERE ID_PRODUIT = ?", new Object[] { productId },
				String.class);
	}

	public String getAttributeCode(long productId, String attributeCode) {
		try {
			String prefix = jdbcTemplate.queryForObject("SELECT PREFIX FROM PRODUIT WHERE ID_PRODUIT = ?", new Object[] { productId }, String.class);
			
			String newAttributCode = jdbcTemplate.queryForObject("SELECT CODE_ATTRIBUT FROM ATTRIBUT WHERE ID_PRODUIT = ? AND CODE_ATTRIBUT = ?",
					new Object[] { productId, (prefix != null ? prefix : "" ) + attributeCode }, String.class);
			
			return newAttributCode;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String getAttributeCode(String productCode) {
		try {
			return jdbcTemplate.queryForObject("SELECT CODE_ATTRIBUT FROM ATTRIBUT WHERE CODE_ATTRIBUT = ?",
					new Object[] { productCode }, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String getAttributeDescription(String attributeCode) {
		try {
			return jdbcTemplate.queryForObject("SELECT LIBELLE_ATTRIBUT FROM ATTRIBUT WHERE CODE_ATTRIBUT = ?",
					new Object[] { attributeCode }, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String getAttributeValueCode(String attributeCode, String value) {
		try {
			return jdbcTemplate.queryForObject("SELECT CODE_VALEUR FROM VALEUR_ATTRIBUT WHERE CODE_ATTRIBUT = ? AND LIBELLE_VALEUR = ?",
					new Object[] { attributeCode, value }, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}

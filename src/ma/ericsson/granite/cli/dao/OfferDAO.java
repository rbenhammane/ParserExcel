package ma.ericsson.granite.cli.dao;

public interface OfferDAO {
	
	public String getOfferCode(int idOffer);
	
	public int getProductId(int idOffer, String productCode);
	
	public String getProductDescription(int productId);
	
	public String getAttributeCode(long productId, String productCode);
	
	public String getAttributeCode(String productCode);
	
	public String getAttributeDescription(String attributeCode);
	
	public String getAttributeValueCode(String attributeCode, String value);

}

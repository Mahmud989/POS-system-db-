// 
// Decompiled by Procyon v0.5.36
// 

package product.res;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import java.awt.Component;
import javax.swing.JOptionPane;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public enum R
{
    loginBTN(""), 
    loginLabel(""), 
    loginPassword(""), 
    closeBTN(""), 
    DatabaseConnErr(""), 
    ServerConnectionErr(""), 
    UserOnlineErr(""), 
    loginErrorMsg(""), 
    loginSuccessMsg(""), 
    ProductNotFound(""), 
    ProductFound(""), 
    searchBTN(""), 
    dates(""), 
    typeLBL(""), 
    ForSale_date(""), 
    seller_name(""), 
    customer_name(""), 
    SaleDateLBL(""), 
    ProductPropeties(""), 
    Price(""), 
    sale_id(""), 
    check_out(""), 
    Pay_BTN(""), 
    Qaliq_LBL(""), 
    LabelForProductSearchInput(""), 
    idOrName(""), 
    PayTypeLBL(""), 
    DeleteRowBTN(""), 
    ShowSaveMsg(""), 
    QaimeBTN("");
    
    private String value;
    
    private R(final String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public static void setLanguage(final String lang) {
        try {
            final String dataFolder = System.getProperty("user.home") + "\\appdata\\local\\iqservice\\res\\strings";
            final File fXmlFile = new File(dataFolder + "/" + lang + ".xml");
            final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            final Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            final NodeList nList = doc.getElementsByTagName("string");
            for (int temp = 0; temp < nList.getLength(); ++temp) {
                final Node nNode = nList.item(temp);
                if (nNode.getNodeType() == 1) {
                    final Element eElement = (Element)nNode;
                    final String attribute = eElement.getAttribute("name");
                    switch (attribute) {
                        case "loginBTN": {
                            R.loginBTN.value = eElement.getTextContent();
                            break;
                        }
                        case "loginLabel": {
                            R.loginLabel.value = eElement.getTextContent();
                            break;
                        }
                        case "loginPassword": {
                            R.loginPassword.value = eElement.getTextContent();
                            break;
                        }
                        case "closeBTN": {
                            R.closeBTN.value = eElement.getTextContent();
                            break;
                        }
                        case "DatabaseConnErr": {
                            R.DatabaseConnErr.value = eElement.getTextContent();
                            break;
                        }
                        case "ServerConnectionErr": {
                            R.ServerConnectionErr.value = eElement.getTextContent();
                            break;
                        }
                        case "UserOnlineErr": {
                            R.UserOnlineErr.value = eElement.getTextContent();
                            break;
                        }
                        case "loginErrorMsg": {
                            R.loginErrorMsg.value = eElement.getTextContent();
                            break;
                        }
                        case "loginSuccessMsg": {
                            R.loginSuccessMsg.value = eElement.getTextContent();
                            break;
                        }
                        case "ProductNotFound": {
                            R.ProductNotFound.value = eElement.getTextContent();
                            break;
                        }
                        case "ProductFound": {
                            R.ProductFound.value = eElement.getTextContent();
                            break;
                        }
                        case "searchBTN": {
                            R.searchBTN.value = eElement.getTextContent();
                            break;
                        }
                        case "dates": {
                            R.dates.value = eElement.getTextContent();
                            break;
                        }
                        case "typeLBL": {
                            R.typeLBL.value = eElement.getTextContent();
                            break;
                        }
                        case "ForSale_date": {
                            R.ForSale_date.value = eElement.getTextContent();
                            break;
                        }
                        case "seller_name": {
                            R.seller_name.value = eElement.getTextContent();
                            break;
                        }
                        case "customer_name": {
                            R.customer_name.value = eElement.getTextContent();
                            break;
                        }
                        case "SaleDateLBL": {
                            R.SaleDateLBL.value = eElement.getTextContent();
                            break;
                        }
                        case "ProductPropeties": {
                            R.ProductPropeties.value = eElement.getTextContent();
                            break;
                        }
                        case "Price": {
                            R.Price.value = eElement.getTextContent();
                            break;
                        }
                        case "sale_id": {
                            R.sale_id.value = eElement.getTextContent();
                            break;
                        }
                        case "check_out": {
                            R.check_out.value = eElement.getTextContent();
                            break;
                        }
                        case "Pay_BTN": {
                            R.Pay_BTN.value = eElement.getTextContent();
                            break;
                        }
                        case "Qaliq_LBL": {
                            R.Qaliq_LBL.value = eElement.getTextContent();
                            break;
                        }
                        case "LabelForProductSearchInput": {
                            R.LabelForProductSearchInput.value = eElement.getTextContent();
                            break;
                        }
                        case "idOrName": {
                            R.idOrName.value = eElement.getTextContent();
                            break;
                        }
                        case "PayTypeLBL": {
                            R.PayTypeLBL.value = eElement.getTextContent();
                            break;
                        }
                        case "DeleteRowBTN": {
                            R.DeleteRowBTN.value = eElement.getTextContent();
                            break;
                        }
                        case "ShowSaveMsg": {
                            R.ShowSaveMsg.value = eElement.getTextContent();
                            break;
                        }
                        case "QaimeBTN": {
                            R.QaimeBTN.value = eElement.getTextContent();
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}

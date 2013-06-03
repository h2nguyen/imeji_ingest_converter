/**
 * 
 */
package core.vo.imeji;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hnguyen
 */
@XmlRootElement(name = "items", namespace = "http://imeji.org/terms")
public class Items implements Cloneable
{
    private List<Item> item;

    public Items()
    {
    }

    public Items(Collection<Item> items)
    {
        this.setItem(new ArrayList<Item>(items));
    } 

    /**
     * @return the items
     */
    @XmlElement(name = "item", namespace = "http://imeji.org/terms")
    public List<Item> getItem()
    {
        return item;
    }

    /**
     * @param items the items to set
     */
    public void setItem(List<Item> item)
    {
        this.item = item;
    }
    
    public Items clone() throws CloneNotSupportedException {

    	List<Item> clonedItemList = new ArrayList<Item>();
    	
    	for (Item item : this.getItem()) {
    		clonedItemList.add(item);
		}
   	
        return new Items(clonedItemList);
      }
}

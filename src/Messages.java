import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author psteger
 * 
 */
public class Messages {
	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
		// empty block, left for consistency
	}

	/**
	 * @param key
	 *            given String
	 * @return searched String
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}

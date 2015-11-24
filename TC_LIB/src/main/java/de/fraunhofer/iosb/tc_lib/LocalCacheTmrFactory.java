package de.fraunhofer.iosb.tc_lib;

import de.fraunhofer.iosb.tc.LocalCache;
import de.fraunhofer.iosb.tc.LocalCacheFactory;
import org.slf4j.Logger;


/**
 * @author mul (Fraunhofer IOSB)
 */
public class LocalCacheTmrFactory implements LocalCacheFactory {

    /**
     * @param logger reference to the logger
     * @return a local cache TMR or null in case of a problem
     */
    @Override
    public LocalCache getLocalCache(final IVCT_RTI ivct_rti, final Logger logger, final TcParam tcParam) {

        try {
            return new LocalCacheTmr(logger, ivct_rti);
        }
        catch (final Exception e) {
            return null;
        }
    }
}

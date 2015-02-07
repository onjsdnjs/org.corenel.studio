package org.corenel.core.disruptor.translator;

import org.corenel.core.common.helper.ServiceHelperHolder;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * @author Á¤¼ö¿ø
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class DefaultEventTranslatorOneArg<T,A> implements EventTranslatorOneArg<T,A> {

	@Override
	public void translateTo(T messageEvent, long sequence, A message) {
		((ServiceHelperHolder)messageEvent).setServiceHelper(message);
	}

}

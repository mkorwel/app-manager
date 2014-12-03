package pl.mkorwel.app.manager.application.usecase.model.request;

import java.io.Serializable;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;

public class ApplicationFindRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int page;

	private final int size;

	private final Sort sort;

	public ApplicationFindRequest(int page, int size, String sortAttribute,
			Direction sortDirection) {
		this.page = page;
		this.size = size;
		if (!StringUtils.isEmpty(sortAttribute)) {
			this.sort = new Sort(sortDirection != null ? sortDirection
					: Direction.ASC, sortAttribute);
		} else {
			this.sort = null;
		}
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}

	public Sort getSort() {
		return sort;
	}

}

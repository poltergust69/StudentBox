export interface SearchProps {}

export interface EmptyProps {}

export interface PaginationState {
  pageIndex: number;
  pageSize: number;
  name: string | null;
  sortDirection: string;
}

export interface PageModel<T> {
  payload: T[];
  pageIndex: number;
  pageSize: number;
  totalPageCount: number;
}

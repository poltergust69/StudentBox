import { UserModel } from "../Auth/AuthInterfaces";
import { PaginationState } from "../Shared/SharedInterfaces";

export interface ForumState {
  posts: PostModel[];
  totalPageCount: number;
  pagination: PaginationState;
  repliesDialogOpened: boolean;
  selectedPost: PostModel | null;
}

export interface PostModel {
  id: string;
  title: string;
  content: string;
  createdAt: number;
  modifiedAt: number;
  likes: number;
  likedByCurrentUser: boolean;
  replies: ReplyModel[];
  author: UserModel;
}

export interface ReplyModel {
  id: string;
  content: string;
  author: UserModel;
  likes: number;
  likedByCurrentUser: boolean;
}

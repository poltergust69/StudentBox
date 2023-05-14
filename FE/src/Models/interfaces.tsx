import { ArrowFunction } from "typescript"

export interface NavbarProps{
    selected?: Number,
    changeCounter: number
}

export interface SearchProps{}

export interface EmptyProps{}

export interface AuthManagerProps{
    callback: () => void
}

export interface SearchState{
    dataList: SearchItemModel[],
    loading: boolean,
    extended: boolean,
    type: SearchType,
    error: string | null,
    hasError: boolean,
    artistQuery: string,
    groupQuery: string,
    reload: boolean
}

export interface SearchPlaceholderListProps{
    count: number
}

export interface SearchListProps{
    dataList: SearchItemModel[]
}

export interface SearchItemModel{
    resourceUrl: string,
    name: string,
    thumbnailUrl: string,
    visible: boolean
}

export enum SearchType{
    Artists, Groups
}

export interface DetailsState{
    item: DetailsModel | null,
    descriptionDialogShowing: boolean,
    selectedTab: DetailsTabs,
    selectedTabPage: number,
    tabDialogOpen: boolean,
    tabDialogOpenIdentifier: string | null
}

export enum DetailsTabs{
    Albums, Songs, Bands, Genres
}

export interface DetailsModel{
    url: string,
    stageName: string,
    realName: string,
    description: string,
    wikiUrl: string,
    webpageUrl: string,
    dateOfBirth: string,
    thumbnailUrl: string,
    songs: SongModel[],
    albums: AlbumModel[],
    bands: ResourceModel[],
    genres: ResourceModel[]
}

export interface SongModel{
    url: string,
    name: string,
    thumbnailUrl: string,
    duration: string,
    releaseDate: string,
    album: string
}

export interface AlbumModel{
    url: string,
    name: string,
    thumbnailUrl: string,
    description: string,
    releaseDate: string,
    songs: string[],
    awards: string[]
}

export interface ResourceModel{
    url: string,
    label: string
}

export interface AuthData{
    id: string,
    username: string,
    role: RoleModel,
    email: string,
    firstName: string,
    lastName: string
}

export interface RoleModel{
    id: string,
    name: string,
    authority: string
}

export interface LoginState{
    user: string,
    password: string,
    hasError: boolean,
    error: string | null,
    isLoggedIn: boolean
}
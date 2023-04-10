export interface Person {
  id: number;
  image: string;
  firstName: string;
  lastName: string;
  email: string;
  address: string;
  phone: string;
  linkedin: string;
  company: Company | null;
  offers: Offer[];
}

export interface Company {
  dateCreation: Date;
  name: string;
  address: string;
  description: string;
  numberOfEmployees: number;
  website: string;
  linkedin: string;
}

export interface Offer {
  title: string;
  createDate: Date;
  description: string;
  skills: string[];
}

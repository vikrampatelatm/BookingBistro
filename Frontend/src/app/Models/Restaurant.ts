
export interface Capacity {
    tableType: string;
    numberOfTables: number;
  }
  
  export interface Restaurant {
    id: number;
    name: string;
    cuisines: string;
    location: string;
    workingDays: string;
    openTime: string; 
    closeTime: string;
    timeSlotInterval: number;
    capacities: Capacity[];
    rating: number;
  }
  
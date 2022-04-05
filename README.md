# A2

Project for Assignment 2

- [x] Create a new member with a name and a personal number, a unique member id should be generated and assigned to the new member. The member id should be something that could be printed on a small membership-card and handled by a human mind, it should be up to 6 alpha-numeric characters.

- [x] Verbose list: name, personal number, member id and boats with boat information.

- [x] Compact list: name, member id and number of boats.

- [x] Delete a member.

- [x] Change a member's information.

- [x] Look at a specific members information.

- [x] Register a new boat for a member the boat should have a type (Sailboat, Motorsailer, kayak/Canoe, Other) and a length.

- [x] Delete a boat.

- [x] Change a boat’s information.

- [x] Prepare the design for persistence, i.e. add a persistence interface. Implement a hard coded "loading" of some members with boats, i.e. create some hard-coded data. You should not implement any persistent loading or saving to file or database etc. for the passing grade.

## From Tobias Ohlsson @tohto:

I have found the following problems in the submission.
- [ ] Object-orientation issues
- [ ] Controller responsibility in View
- [ ] Controller responsibility in Model
- [x] View Responsibility in Model
- [X] View Responsibility in Controller
- [X] Hidden Dependencies
- [X] Encapsulation Problem
- [ ] Model responsibility in View/Controller
- [ ] Lack of Code Quality
- [ ] Architectural Dependency Violations
- [X] Class Diagram Notation/Code Conformance
- [X] Sequence Diagram Notation/Code Conformance

If the `Needs completion` label is set then your task is to understand the problem(s) and address them properly. Note that during the re-examination other/additional issues may be found so it is a good idea to check everything again and of course update diagrams.

composition/aggregation in class diagram is to be avoided, use the normal association instead. I miss the packages in the class diagram it makes it very hard to spot potential problems.

I guess you can have several members that get the same memberid?

There are some good parts in the design of the model (AllData->Member->Boats) but, overall this is not well designed with magic constants all over the application, mixed responsibilities in architectural modules and in general strange things going on.

## Problems:

- [ ] View Responsibility in Model
- [ ] View Responsibility in Controller
- [ ] Hidden Dependencies
- [ ] Encapsulation Problem
- [ ] Class Diagram Notation/Code Conformance
- [ ] Sequence Diagram Notation/Code Conformance
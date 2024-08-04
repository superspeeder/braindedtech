when I come back:

set up material.schema.json allowed-parts to have partConfiguration settings (i.e. allow ingots to be bars without needing a new, fancy partConfiguration, etc.) based on the partConfiguration specification.


# Where data is defined


# How data is loaded

Some data is loaded at server load/reload times (for example: recipes).
However, some data is instead loaded in the mod constructor (needs to be loaded prior to registry events firing).
Some data might be loaded during specific registry events.